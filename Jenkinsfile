pipeline {
    agent any

    tools {
        // Ensure "Maven3" is configured in Jenkins Global Tool Configuration
        maven 'Maven3'
    }

    stages {
        stage('Cleanup') {
            steps {
                echo 'Cleaning up previous builds...'
                bat 'mvn clean'
            }
        }

        stage('Run API Tests') {
            steps {
                echo 'Executing RestAssured Cucumber Tests...'
                // Running maven test. Note: the Extent Adapter handles report generation automatically.
                bat 'mvn test'
            }
        }

        stage('Publish Extent Report') {
            steps {
                echo 'Publishing Extent Reports to Jenkins UI...'
                publishHTML([
                    allowHeaderStrategy: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'htmlreports/HTML_20Report',
                    reportFiles: 'index.html',
                    reportName: 'Extent API Report',
                    reportTitles: 'RestAssured Test Execution'
                ])
            }
        }
    }

    post {
        always {
            echo 'Archiving Test Results...'
            junit '**/target/surefire-reports/*.xml'
        }
        success {
            echo 'Tests Passed Successfully!'
        }
        failure {
            echo 'Tests Failed. Check the Extent Report for details.'
        }
    }
}