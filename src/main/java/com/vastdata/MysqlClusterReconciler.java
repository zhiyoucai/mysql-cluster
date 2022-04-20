package com.vastdata;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.javaoperatorsdk.operator.api.reconciler.Context;
import io.javaoperatorsdk.operator.api.reconciler.Reconciler;
import io.javaoperatorsdk.operator.api.reconciler.UpdateControl;

public class MysqlClusterReconciler implements Reconciler<MysqlCluster> { 
  private final KubernetesClient client;

  public MysqlClusterReconciler(KubernetesClient client) {
    this.client = client;
  }

  // TODO Fill in the rest of the reconciler

  @Override
  public UpdateControl<MysqlCluster> reconcile(MysqlCluster resource, Context context) {
    // TODO: fill in logic

    return UpdateControl.noUpdate();
  }
}

