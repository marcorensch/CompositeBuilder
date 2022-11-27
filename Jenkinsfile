// declarative syntax
// noinspection GroovyAssignabilityCheck
pipeline {
   agent any
   tools {
      // ** NOTE: This 'Maven 3' Maven tool must be configured in the global configuration.
      maven 'Maven 3'
   }
   stages {
      stage ('Build') {
         steps {
            sh 'mvn -Dmaven.test.failure.ignore clean org.jacoco:jacoco-maven-plugin:prepare-agent package'
         }
         post {
            // only run if previous steps in current stage successful
            success {
               junit 'target/surefire-reports/**/*.xml'
               archiveArtifacts 'target/*.jar'
            }
         }
      }
      stage('Quality analysis') {
         steps {
            // ** NOTE: This 'SonarScanner4' SonarScanner tool must be configured in the global configuration.
            withSonarQubeEnv('SonarScanner4') {
               sh "mvn -Dmaven.test.skip=true clean package sonar:sonar"
            }
         }
      }
   }
}