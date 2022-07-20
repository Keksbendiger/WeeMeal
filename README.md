
### WeeMeal
## Install and register GitLab Runner:

### Normal install:

```
At first start your Docker. On Mac and Windows, you can use the DockerDesktop app.
```shell  
docker volume create gitlab-runner-config
```  

```shell  
docker run --rm -it \
-v gitlab-runner-config:/etc/gitlab-runner \  
gitlab/gitlab-runner:latest register  
```  
You need to type in some information.
Important are: URL, Token, Executor and Docker image.

- Registration URL: `https://git.ai.fh-erfurt.de/`
- Registration token: `XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX`  
  You can find this information here: https://git.ai.fh-erfurt.de/da5138st/weemeal/-/settings/ci_cd#js-runners-settings

- Executor: `Docker`
- Docker image: `gitlab/gitlab-runner:latest`

```shell  
docker run -d --name gitlab-runner --restart always \-v /var/run/docker.sock:/var/run/docker.sock \  
-v gitlab-runner-config:/etc/gitlab-runner \  
gitlab/gitlab-runner:latest  
```

Now your Runner should be up and running.

### For MacOS M1:

```shell  
sudo curl --output /usr/local/bin/gitlab-runner "https://gitlab-runner-downloads.s3.amazonaws.com/latest/binaries/gitlab-runner-darwin-arm64"
```

```shell  
sudo chmod +x /usr/local/bin/gitlab-runner
```

```shell  
gitlab-runner register
```

You need to type in some information.
Important are: URL, Token, Executor.

- Registration URL: `https://git.ai.fh-erfurt.de/`
- Registration token: `XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX`  
  You can find this information here: https://git.ai.fh-erfurt.de/da5138st/weemeal/-/settings/ci_cd#js-runners-settings

- Executor: `shell`
