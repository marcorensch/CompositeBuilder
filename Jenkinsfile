// using scripted pipeline syntax
/*node {
   def mvnHome
   stage('Preparation') {
      // Get some code from a GitHub repository
      checkout scm
      // ** NOTE: This 'Maven 3' Maven tool must be configured in the global configuration.
      mvnHome = tool 'Maven 3'
   }
   stage('Build') {
      sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean org.jacoco:jacoco-maven-plugin:prepare-agent package"
   }
   stage('Results') {
      junit 'target/surefire-reports/TEST-*.xml'
      archive 'target/*.jar'
   }
   *//*stage('SonarQube analysis') {
       // ** NOTE: This 'SonarQube Scanner 3' tool must be configured in the global configuration.
       def scannerHome = tool 'SonarQube Scanner 3';
       withSonarQubeEnv() {
          sh "${scannerHome}/bin/sonar-scanner"
       }
   } *//*
}*/

// declarative syntax
// noinspection GroovyAssignabilityCheck
pipeline {
   agent any
   tools {
      maven 'Maven 3'
   }
   stages {
      stage ('Initialize') {
         steps {
            sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
         }
      }

      stage ('Build') {
         steps {
            sh 'mvn -Dmaven.test.failure.ignore clean org.jacoco:jacoco-maven-plugin:prepare-agent package'
         }
         post {
            success {
               junit 'target/surefire-reports/**/*.xml'
            }
         }
      }
   }
}
