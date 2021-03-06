/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for
 * license information.
 */
package com.microsoft.azure.spring.cloud.autoconfigure.telemetry;

import com.microsoft.applicationinsights.core.dependencies.apachecommons.codec.digest.DigestUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.net.InetAddress;
import java.net.NetworkInterface;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MacAddressHelper {

    private static final String UNKNOWN_MAC = "Unknown-Mac-Address";

    private static final String hashedMacAddress = computeHashedMacAddress();

    private static String computeHashedMacAddress() {
        try {
            InetAddress host = InetAddress.getLocalHost();
            byte[] macBytes = NetworkInterface.getByInetAddress(host).getHardwareAddress();

            return DigestUtils.sha256Hex(macBytes);
        } catch (Exception ignore) {
            return UNKNOWN_MAC;
        }
    }

    public static String getHashedMacAddress() {
        return hashedMacAddress;
    }
}
