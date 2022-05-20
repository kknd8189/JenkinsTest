def RepositoryDir = 'Repository'
def JenkinsScriptDir = 'JenkinsScriptDir'
def JenkinsScriptGitURL = 'https://github.com/kknd8189/JenkinsTest'
def ServerDir = 'Server'

pipeline
{
	parameters
	{
		string defaultValue: 'http://repositories.actionsquare.corp/svn/b2r/trunk/BladeRA/Server', description: '서버 SVN 주소', name: 'SERVER_SVN_URL', trim: false
		string defaultValue: 'HEAD', description: 'Server svn revision id', name: 'SERVER_REVISION_ID', trim: false
		booleanParam defaultValue: false, description: '리빌드여부', name: 'REBUILD_ENGINE'
	}

	agent
	{
		node
		{
			customWorkspace 'C:\\BladeRA'
			label 'TEST_LABEL'
		}
	}


	stages 
	{     
	stage('Initialize') 
	{
            steps {
                script {
                                   
                    def exists = fileExists 'Source'
                    if (!exists){
                        new File(RepositoryDir).mkdir()
                        new File(JenkinsScriptDir).mkdir()
                    }
                }
            }
    }
	stage('Jenkins Script checkout') {
            steps {
            
                dir(JenkinsScriptDir) {

                 checkout([$class: 'GitSCM', branches: [[name: 'main']], extensions: [], userRemoteConfigs: [[credentialsId: 'kknd8189', url: JenkinsScriptGitURL]]])
                 bat 'echo Jenkins Script checkout - fininsh...'
                }
            }
        }
		stage('Test checkout')
		{   
			steps
			{
				dir(ServerDir)
				{
					checkout([$class: 'GitSCM', branches: [[name: 'main']], extensions: [], userRemoteConfigs: [[credentialsId: 'kknd8189', url: 'https://github.com/kknd8189/teamportfolio-SkotpillGrim.git']]])
				}
			}
		}

		stage('Test Build')
		{
			steps
			{
				script
				{
					build 'C:\\BladeRA\\Server\\pow-스캇필그림\\WindowAPI.sln'
				}
			}
		}
	}
}