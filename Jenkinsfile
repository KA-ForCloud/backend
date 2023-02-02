pipeline {
    agent any
    tools {
        gradle 'gradle'
    }
    stages {
        stage('Git Clone') {
            steps {
                git branch: 'cicdtest', url: 'https://github.com/KA-ForCloud/backend.git'
            }
        }
        stage('BE-Build') {
            steps {
                    sh "./gradlew clean build"
            }
        }
    }
}
