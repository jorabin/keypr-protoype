# We build a FAT jar and dockerize it
Note the following https://medium.com/holisticon-consultants/dont-build-fat-jars-for-docker-applications-6252a5571248
```bash
docker build --tag keypr-server .  
```

```bash
docker run -p 8080:8080 keypr-server
```

Running in Google Cloud Run:

`gcloud auth login`

`gcloud auth configure-docker europe-west2-docker.pkg.dev`

`docker push europe-west2-docker.pkg.dev/keypr-server-2023-11-30/keypr-server/keypr-server`

`gcloud builds submit --tag europe-west2-docker.pkg.dev/keypr-server-2023-11-30/keypr-server/keypr-server --project keypr-server-2023-11-30`

    `API [cloudbuild.googleapis.com] not enabled on project [keypr-server-2023-11-30]. Would you like to enable and retry (this will take a few minutes)? (y/N)?`

[Cloud Run Console](https://console.cloud.google.com/run?project=keypr-server-2023-11-30)

https://cloud.google.com/resource-manager/docs/creating-managing-projects

Create a project https://console.cloud.google.com/cloud-resource-manager


Create a Repository https://console.cloud.google.com/artifacts/browse/keypr-server-2023-11-30?project=keypr-server-2023-11-30

[Review Logs](https://console.cloud.google.com/logs/)

[Handy Guide](https://medium.com/@taylorhughes/how-to-deploy-an-existing-docker-container-project-to-google-cloud-run-with-the-minimum-amount-of-daca0b5978d8)