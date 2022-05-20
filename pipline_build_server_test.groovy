def RepositoryDir = 'Repository'
def JenkinsScriptDir = 'JenkinsScriptDir'
def JenkinsScriptGitURL = 'http://repositories.actionsquare.corp:3000/b2r/Tools.git'
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
		stage('Test CheckOut') 
		{      
			steps
			{
				script
				{
					new File(RepositoryDir).mkdir()
					new File(ServerDir).mkdir()  
				}
			}
		}

		stage('Test Build')
		{   
			dir(ServerDir)
			{
				checkout([$class: 'GitSCM', branches: [[name: 'main']], extensions: [], userRemoteConfigs: [[credentialsId: 'kknd8189', url: 'https://github.com/kknd8189/teamportfolio-SkotpillGrim.git']]])
			}
		}
		
	}
}