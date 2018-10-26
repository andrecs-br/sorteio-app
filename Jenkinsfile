#!groovy

import hudson.model.*

node {
	def mvnHome
	def props = readProperties  file: 'src/main/resources/application.properties'
	def applicationPort = props['server.port']
	def appContext = props['server.contextPath']

	try{
		stage('Preparation') { // for display purposes
			echo "########## STAGE PREPARATION ##########"
			echo "Usuario: "+User.current()
			echo "Baixando projeto do GIT "
			git credentialsId: '561fc85a-2a94-4880-a299-830093dc98f6', url: GitProjectUrl, branch: GitBranch
			mvnHome = tool 'apache-maven-3.0.4'
		}
		stage('Surefire Test'){
			echo "########## UNIT TESTs ##########"
			sh "'${mvnHome}/bin/mvn' " + MavenParameter1
		}
		stage('Package') {
			echo "########## PACKAGE ##########"
		    sh "'${mvnHome}/bin/mvn' " + MavenParameter2
		}
		stage('Failsafe Test') {
			echo "########## IT TESTS ##########"
		    sh "'${mvnHome}/bin/mvn' " + MavenParameter3
		}
		stage('SonarQube analysis') {
			echo "########## SONARQUBE ANALYSIS ##########"
			def scannerHome = tool 'SonarQube Scaner';
			echo "Enviando projeto para o SonarQube"
			withSonarQubeEnv('Sonar') {
				sh "${scannerHome}/bin/sonar-scanner " + SonarParameter
			}
			echo "Coletando o resultado da analise do SonarQube"
			resultSonar = sh returnStatus: true, script: '''if  curl -sL -w %{http_code} ''' + UrlSonarCheckStatus + ''' | grep \\"projectStatus\\":{\\"status\\":\\"ERROR\\" -c > 0 ;
			then 
				exit 1
			fi;'''
			if(resultSonar == 1){
			   msg = 'Reprovado no sonar - :('
			   error msg
			}
		}

		stage('Deploy') {
			echo "########## DEPLOY ##########"
		    def workspace = pwd()
			def deployFile = findFiles glob: '**/' + PackageName + '*.jar'
			try{
				sh 'ps -ef | grep ' + PackageName + ' | grep -v grep | awk \'{print $2}\' | xargs kill'
			}catch(e){
			    echo 'Falha ao matar o processo'
			}
		    dir(ApplicationDeployDirectory) {
		    	sh 'cp /opt/jenkins/home/jobs/Deploy-Prazo-Pedido-APP/workspace/target/' + deployFile[0].name + ' .'
		    	sh 'chmod 775 ' + deployFile[0].name
                sh 'java -Dspring.profiles.active=dsp -Xmx128m -Xms128m -jar ' + deployFile[0].name + ' &'
            }
		}
		stage('Wait For Server'){
		    sleep 30
		}
		stage('Smoke Test') {
		    echo "########## SMOKE TEST ##########"
		    resultSmoke = sh returnStatus: true, script: '''if  curl -sL -w %{http_code} ''' + 'http://' + ServerIP +':'+ applicationPort + appContext + '/health' + ''' | grep {\\"status\\":\\"UP\\" -c > 0 ;
			then 
				exit 1
			fi;'''
			if(resultSmoke != 1){
			   msg = 'Falhou no smoke test - :('
			   error msg
			}
		}
		stage('Send Results') {
			echo "########## SEND RESULTS ##########"
			if(currentBuild.previousBuild != null 
				&& currentBuild.previousBuild.result == 'FAILURE'){
				slackSend channel: '#jenkins', color: 'good', message: JOB_NAME + ': Falha do build anterior resolvida.  Usuario: '+User.current(), teamDomain: 'vrdesenv', token: 'Kgs98J8t5bh6s0v4FNj1Qlb1'
			}
		}
	}catch(e){
		currentBuild.result = "FAILED"
		notifyFailed(e.message)
		throw e
	}
}

def notifyFailed(message) {
	lastCommitAuthor =  sh returnStdout: true, script: "git --no-pager show -s --format='%ae'"
	slackSend channel: '#jenkins', color: 'danger', message: JOB_NAME+ ': Falhou.  Usuario: '+User.current()+' Ultimo commit realizado por: ' + lastCommitAuthor + ' Mensagem: '+ message, teamDomain: 'vrdesenv', token: 'Kgs98J8t5bh6s0v4FNj1Qlb1'
}