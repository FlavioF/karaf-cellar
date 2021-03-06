/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.karaf.cellar.kubernetes;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ManagedServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Hashtable;

public class Activator implements BundleActivator {

    private ServiceRegistration serviceRegistration;

    private final static Logger LOGGER = LoggerFactory.getLogger(Activator.class);

    public void start(BundleContext bundleContext) throws Exception {
        Hashtable<String, Object> properties = new Hashtable<String, Object>();
        properties.put(Constants.SERVICE_PID, "org.apache.karaf.cellar.kubernetes");
        KubernetesDiscoveryServiceFactory kubernetesDiscoveryServiceFactory = new KubernetesDiscoveryServiceFactory(bundleContext);
        serviceRegistration = bundleContext.registerService(ManagedServiceFactory.class.getName(), kubernetesDiscoveryServiceFactory, properties);
    }

    public void stop(BundleContext bundleContext) throws Exception {
        if (serviceRegistration != null) {
            serviceRegistration.unregister();
            serviceRegistration = null;
        }
    }

}
