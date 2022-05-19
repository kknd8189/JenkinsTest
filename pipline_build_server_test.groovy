def RepositoryDir = 'Repository'
def JenkinsScriptDir = 'JenkinsScriptDir'
def JenkinsScriptGitURL = 'http://repositories.actionsquare.corp:3000/b2r/Tools.git'

pipeline
{
    parameters
    {
        string defaultValue: 'http://repositories.actionsquare.corp/svn/b2r/trunk/BladeRA/Server', description: '���� SVN �ּ�', name: 'SERVER_SVN_URL', trim: false
        string defaultValue: 'HEAD', description: 'Server svn revision id', name: 'SERVER_REVISION_ID', trim: false
        booleanParam defaultValue: false, description: '�����忩��', name: 'REBUILD_ENGINE'
    }

    agent
    {
        node
        {
            label 'TEST_LABEL'
        }
    }


    stages 
    { 
        stage('Test CheckOut') 
        {      
            steps
            {
                  checkout([$class: 'GitSCM', branches: [[name: 'main']], extensions: [], userRemoteConfigs: [[credentialsId: 'kknd8189', url: 'https://github.com/kknd8189/teamportfolio-SkotpillGrim.git']]])
            }
        }

        stage('Test CheckOut2')
        {
            steps
            {
                checkout([$class: 'GitSCM', branches: [[name: 'main']], extensions: [], userRemoteConfigs: [[credentialsId: 'kknd8189', url: 'https://github.com/kknd8189/teamportfolio-SkotpillGrim.git']]])
            }

             bat 'echo test finish'
        }
    }
}