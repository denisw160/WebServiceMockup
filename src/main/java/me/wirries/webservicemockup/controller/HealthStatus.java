package me.wirries.webservicemockup.controller;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * A small class for representing the state.
 *
 * @author denisw
 * @version 1.0
 * @since 26.03.20
 */
public class HealthStatus {

    private final String version;
    private final String state;

    public HealthStatus(String version, String state) {
        this.version = version;
        this.state = state;
    }

    public String getVersion() {
        return version;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("version", version)
                .append("state", state)
                .toString();
    }

}
