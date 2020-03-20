
def notifyBuild(String buildStatus = 'STARTED') {
  // build status of null means successful
  buildStatus =  buildStatus ?: 'SUCCESSFUL'

  // Default values

  def colorName = 'RED'
  def colorCode = '#FF0000'
  def subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
  def sonar_url="Sonar_TravelNShare report is available at : https://cedricp.pagekite.me/"
  def sonar_id="Log in with Login:admin / Password:admin"
  def summary = "${subject} (${env.BUILD_URL}) ${sonar_url} ${sonar_id}"
  def details = """<p>STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
    <p>Check console output at &QUOT;<a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>&QUOT;</p>
    """

  // Override default values based on build status
  if (buildStatus == 'STARTED') {
    color = 'YELLOW'
    colorCode = '#FFFF00'
  } else if (buildStatus == 'SUCCESSFUL') {
    color = 'GREEN'
    colorCode = '#00FF00'
  } else {
    color = 'RED'
    colorCode = '#FF0000'
  }

  // Send notifications
  slackSend (color: colorCode, message: summary)
  }
  
pipeline {


  agent any 
	stages{
          
          stage('Sonar test') {
                steps {
                  withSonarQubeEnv('Sonar_TravelNShare') {
                    bat 'mvn clean install sonar:sonar'
                  }

                }
              }

	} //stages end  
	post{
		always{
        slackSend (color: '#FFFF00', message: "Incoming report")
        bat 'javadoc:javadoc'
		}
		success{
		notifyBuild('SUCCESSFUL')
		}
		failure{
		notifyBuild('ERROR')
	}
  } 
  }//pipeline end