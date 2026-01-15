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
                // Using catchError ensures that even if tests fail, the pipeline
                // continues to the "Publish Extent Report" stage.
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    bat 'mvn test'
                }
            }
        }

        stage('Publish Extent Report') {
            steps {
                echo 'Publishing Extent Reports to Jenkins UI...'
                publishHTML([
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
            // Archiving standard JUnit XML results for Jenkins trend charts
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