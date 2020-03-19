def notifyBuild(String buildStatus = 'STARTED') {
  // build status of null means successful
  buildStatus =  buildStatus ?: 'SUCCESSFUL'
}
  // Default values
  def GIT_REPO='https://github.com/boxf/travelandshare.git'
def mvnHome = tool 'maven-3.6.3'
def dockerTag='development'
  def colorName = 'RED'
  def colorCode = '#FF0000'
  def subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
  def summary = "${subject} (${env.BUILD_URL})"
  def details = """<p>STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
    <p>Check console output at &QUOT;<a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>&QUOT;</p>"""

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

pipeline {

  agent any
	node{
          stage ('Download Code') {
			steps{
              echo "Tag selected: ${gitTAG}"

              echo "Downloading code from: ${GIT_REPO}"

              checkout([$class: 'GitSCM',
                  branches: [[name: gitTAG]],
                  doGenerateSubmoduleConfigurations: false,
                  extensions: [[$class: 'CleanCheckout']],
                  submoduleCfg: [],
                  userRemoteConfigs: [[url: GIT_REPO]]
              ])

              echo "\u2600 BUILD_URL=${env.BUILD_URL}"

              echo "\u2600 workspace=${workspace}"
			  }//steps end
          }

          stage ('Build') {
			steps{
				script{
					if (Boolean.valueOf(skipBuild)) {
					echo "Build is skipped"
              } else {
                  echo "Building"
                  bat "cd ${workspace} && ${mvnHome}/bin/mvn clean install -DskipTests -Dbuild.number=${BUILD_NUMBER}"
              }
			  } //script end
			  }//steps end
          }


          stage('Sonar test') {
                steps {
                  withSonarQubeEnv('Sonar_TravelNShare') {
                    bat 'mvn clean install sonar:sonar'
                  }

                }
              }
			stage('Notify successful') {
				steps{
					notifyBuild('SUCCESSFUL')
				}
			}


	} //stages end
  } //pipeline end