package com.vastdata;

import io.fabric8.kubernetes.api.model.Namespaced;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Version;

@Version("v1alpha1")
@Group("mysqlcluster.vastdata.com")
public class MysqlCluster extends CustomResource<MysqlClusterSpec, MysqlClusterStatus> implements Namespaced {}

