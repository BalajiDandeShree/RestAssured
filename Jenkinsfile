pipeline {
    agent any
    tools { maven 'apache-maven-3.9.6' }
    stages {
        stage('Run Suite 1') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    // Hardcoded to run testng.xml
                    bat 'mvn test -DsuiteXmlFile=src/test/suite/testng.xml'
                }
            }
        }
        stage('Publish Report') {
            steps {
                publishHTML([
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'htmlreports/HTML_20Report',
                    reportFiles: 'index.html',
                    reportName: 'Extent Report - Suite 1'
                ])
            }
        }
    }
    post {
        always {
            // CRITICAL: Archive the report so the Coordinator can see it
            archiveArtifacts artifacts: 'htmlreports/HTML_20Report/**', allowEmptyArchive: false
        }
    }
}