package com.github.neitomic.aws;

import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;

public class AwsProfileCredentialsProvider implements AwsCredentialsProvider {

    private final AwsCredentialsProvider provider;

    public AwsProfileCredentialsProvider(String profileName) {
        this.provider = ProfileCredentialsProvider.builder()
                .profileName(profileName)
                .build();
    }

    /**
     * Constructor required by Athena JDBC 3.x for custom providers.
     * It receives CredentialsProviderArguments split by comma.
     */
    public AwsProfileCredentialsProvider(String... args) {
        String profile = args != null && args.length > 0 ? args[0] : "default";
        this.provider = ProfileCredentialsProvider.builder()
                .profileName(profile)
                .build();
    }

    @Override
    public AwsCredentials resolveCredentials() {
        return provider.resolveCredentials();
    }
}
