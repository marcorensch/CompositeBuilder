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
            // ** NOTE: This 'sonar server' SonarQube server must be configured in the system configuration.
            withSonarQubeEnv('sonar server') {
               sh "mvn -Dmaven.test.skip=true clean package sonar:sonar"
            }
         }
      }
   }
}