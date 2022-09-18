pipeline {
    agent any

    stages {

        stage('Build Project') {
            steps {
                bat "java --version"
                bat "mvn clean"
                echo "Build Selenium Project Successful ...."
            }
        }

        stage('Run Project') {
            steps {
                bat "mvn --version"
                bat "mvn clean compile test -DSUITEFILE=src/main/resources/xml/framework/e2e_login.xml"
                echo "Run Test Project Successfully ...."
            }
        }
    }
    post {
        always {
            publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: '', reportFiles: 'test-output/*.html', reportName: 'HTML Report', reportTitles: ''])
            echo "Selenium Project Run Job Successful ...."
        }
    }
}