package org.apereo.cas.support.geo.config;

import org.apereo.cas.authentication.adaptive.geo.GeoLocationService;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.configuration.support.Beans;
import org.apereo.cas.configuration.support.CasFeatureModule;
import org.apereo.cas.support.geo.google.GoogleMapsGeoLocationService;
import org.apereo.cas.util.spring.boot.ConditionalOnFeature;

import com.google.maps.GaeRequestHandler;
import com.google.maps.GeoApiContext;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScopedProxyMode;

import java.util.concurrent.TimeUnit;

/**
 * This is {@link GoogleMapsGeoCodingConfiguration}.
 *
 * @author Misagh Moayyed
 * @since 5.0.0
 */
@Configuration(value = "CasGeoLocationConfiguration", proxyBeanMethods = false)
@EnableConfigurationProperties(CasConfigurationProperties.class)
@ConditionalOnFeature(feature = CasFeatureModule.FeatureCatalog.GeoLocation)
public class GoogleMapsGeoCodingConfiguration {

    @ConditionalOnMissingBean(name = GeoLocationService.BEAN_NAME)
    @Bean
    @RefreshScope(proxyMode = ScopedProxyMode.DEFAULT)
    public GeoLocationService geoLocationService(final CasConfigurationProperties casProperties) {
        val builder = new GeoApiContext.Builder();
        val properties = casProperties.getGoogleMaps();
        if (properties.isGoogleAppsEngine()) {
            builder.requestHandlerBuilder(new GaeRequestHandler.Builder());
        }
        if (StringUtils.isNotBlank(properties.getClientId()) && StringUtils.isNotBlank(properties.getClientSecret())) {
            builder.enterpriseCredentials(properties.getClientId(), properties.getClientSecret());
        }
        builder.apiKey(properties.getApiKey()).connectTimeout(Beans.newDuration(properties.getConnectTimeout()).toMillis(), TimeUnit.MILLISECONDS);
        return new GoogleMapsGeoLocationService(builder.build());
    }
}
