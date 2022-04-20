# mysql-cluster

本项目的开发目的是为了研究Operator的实现方法。最终希望能够实现MySQL的controller，下面的yaml文件就是本工程的期望：

```yaml
---
apiVersion: v1
kind: Service
metadata:
  name: mysql-service
spec:
  ports:
  - port: 3306
  selector:
    app: mysql
---
apiVersion: v1
kind: PersistentVolume
metadata:
  name: mysql-pv-volume
spec:
  capacity:
    storage: 2Gi
  accessModes:
    - ReadWriteOnce
  storageClassName: manual
  hostPath:
    path: "/usr/local/mysqldata"
---
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: mysql-pv-claim
spec:
  resources:
    requests:
      storage: 2Gi
  accessModes:
    - ReadWriteOnce
  storageClassName: manual
---
apiVersion: v1
kind: ConfigMap
metadata:
  name: mycnf
  labels:
    app: mycnf
data:
  my.cnf: |
    [mysqld]
    datadir=/var/lib/mysql
    socket=/var/lib/mysql/mysql.sock
    pid-file=/var/run/mysqld/mysqld.pid
---
apiVersion: apps/v1
kind: StatefulSet
metadata:
  name: mysql
spec:
  replicas: 1
  serviceName: mysql-service
  selector:
    matchLabels:
      app: mysql
  template:
    metadata:
      labels:
        app: mysql
    spec:
      containers:
      - name: mysql
        image: mysql:5.7.36
        imagePullPolicy: IfNotPresent
        env:
        - name: MYSQL_ROOT_PASSWORD
          value: Xiyangyang2022!
        resources:
          limits:
            memory: "256Mi"
            cpu: "500m"
        ports:
        - containerPort: 3306
        envFrom:
        - configMapRef:
            name: mycnf
        volumeMounts:
        - name:  mysql-persistent-storage
          mountPath:  /usr/local/mysqldata
        - name: mysqlcnf
          mountPath: /etc/my.cnf
          subPath: etc/my.cnf
      volumes:
      - name: mysql-persistent-storage
        persistentVolumeClaim:
          claimName: mysql-pv-claim
      - name: mysqlcnf
        configMap:
          name: mycnf
          items:
          - key: my.cnf
            path: etc/my.cnf
```
