pipeline {
  agent any
  stages {
    stage('Add jenkinsfile to branches') {
      steps {
        echo 'Trying to add stuff'
      }
    }

    stage('sonar analysis') {
      steps {
        withSonarQubeEnv('Sonar_TravelNShare') {
          bat 'mvn clean install sonar:sonar'
        }

      }
    }

  }
}