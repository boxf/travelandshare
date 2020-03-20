
def notifyBuild(String buildStatus = 'STARTED') {
  // build status of null means successful
  buildStatus =  buildStatus ?: 'SUCCESSFUL'

  // Default values

  def colorName = 'RED'
  def colorCode = '#FF0000'
  def subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
  def summary = "${subject} (${env.BUILD_URL})"
  def details = """<p>STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
    <p>Check console output at &QUOT;<a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>&QUOT;</p>
    <P>Sonar_TravelNShare report is available at : https://cedricp.pagekite.me/</p>"""

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
		notifyBuild('STARTED')
		}
		success{
		notifyBuild('SUCCESSFUL')
		}
		failure{
		notifyBuild('ERROR')
	}
  } 
  }//pipeline end