FROM python:3.9-slim-buster

WORKDIR /app

COPY requirements.txt requirements.txt
RUN pip3 install -r requirements.txt

#RUN mkdir -p /app/data/ltc/
ADD ./data ./data

COPY main.py main.py

CMD ["python","main.py"]
