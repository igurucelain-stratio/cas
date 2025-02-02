package org.apereo.cas.support.saml;

import net.shibboleth.utilities.java.support.xml.ParserPool;
import org.opensaml.core.xml.XMLObjectBuilderFactory;
import org.opensaml.core.xml.config.XMLObjectProviderRegistry;
import org.opensaml.core.xml.io.MarshallerFactory;
import org.opensaml.core.xml.io.UnmarshallerFactory;

/**
 * Load the OpenSAML config context.
 *
 * @author Misagh Moayyed
 * @since 4.1
 */
public interface OpenSamlConfigBean {

    /**
     * Default bean name.
     */
    String DEFAULT_BEAN_NAME = "shibboleth.OpenSAMLConfig";

    /**
     * Gets parser pool.
     *
     * @return the parser pool
     */
    ParserPool getParserPool();

    /**
     * Gets builder factory.
     *
     * @return the builder factory
     */
    XMLObjectBuilderFactory getBuilderFactory();

    /**
     * Gets marshaller factory.
     *
     * @return the marshaller factory
     */
    MarshallerFactory getMarshallerFactory();

    /**
     * Gets unmarshaller factory.
     *
     * @return the unmarshaller factory
     */
    UnmarshallerFactory getUnmarshallerFactory();

    /**
     * Gets xml object provider registry.
     *
     * @return the xml object provider registry
     */
    XMLObjectProviderRegistry getXmlObjectProviderRegistry();
}
