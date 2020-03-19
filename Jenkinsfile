def notifyBuild(String buildStatus = 'STARTED') {
  // build status of null means successful
  buildStatus =  buildStatus ?: 'SUCCESSFUL'
}

  // Default values
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
  try {
      node {
          stage ('Download Code') {
              echo "Tag selected: ${gitTAG}"

              def GIT_REPO='https://github.com/boxf/travelandshare.git'

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
          }

          def dockerTag='development'

          stage ('Build') {
              if (Boolean.valueOf(skipBuild)) {
                  echo "Build is skipped"
              } else {
                  echo "Building"
                  def mvnHome = tool 'maven-3.6.3'
                  bat "cd ${workspace} && ${mvnHome}/bin/mvn clean install -DskipTests -Dbuild.number=${BUILD_NUMBER}"
              }
          }

          stage ('Unit Test') {
              if (Boolean.valueOf(skipTests)) {
                  echo "Integration tests were skipped"
              } else {
                  echo "Unit testing"
                  def mvnHome = tool 'maven-3.6.3'
                  bat "cd ${workspace} && ${mvnHome}/bin/mvn surefire:test"
              }
          }
          stage('Sonar test') {
                steps {
                  withSonarQubeEnv('Sonar_TravelNShare') {
                    bat 'mvn clean install sonar:sonar'
                  }

                }
              }
      } // node
      notifyBuild('SUCCESSFUL')
  } // try end
  catch (exc) {
     notifyBuild('ERROR')
  }
  }
