#!/bin/sh

gradle_build()
{
  ./gradlew build
}

docker_build()
{
  docker build --tag biom-backend .
}

docker_run()
{
  docker run --name biom-backend-container -p 8130:8130 biom-backend
}

# jar 빌드 및 docker 이미지 빌드
gradle_build; echo "gradle build 성공" && docker_build; echo "docker 이미지 빌드 성공"

# 실행중인 컨테이너를 찾는다.
CONTAINER_ID=$(docker container ls -a -f "name=biom-backend-container" -q)

# 실행중인 컨테이너가 있으면 삭제한다. 없으면 이미지를 실행한다.
if [ -z "${CONTAINER_ID}" ] # 검색된 컨테이너 id가 없으면
then
  echo "> 현재 구동중인 컨테이너가 없습니다. 서버를 실행합니다."
else
  echo "> docker stop ${CONTAINER_ID}"
  sudo docker stop "${CONTAINER_ID}"
  echo "> docker rm ${CONTAINER_ID}"
  sudo docker rm "${CONTAINER_ID}"
fi

docker_run || echo "실행 실패"