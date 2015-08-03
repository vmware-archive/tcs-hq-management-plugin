/*
 * Copyright (C) 2009-2015  Pivotal Software, Inc
 *
 * This program is is free software; you can redistribute it and/or modify
 * it under the terms version 2 of the GNU General Public License as
 * published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package com.springsource.hq.plugin.tcserver.plugin.serverconfig.environment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Define the set of command line options that must be present in the Windows wrapper.conf, but should not be
 * viewed/edited by end-users.
 * 
 * @since 2.0
 */
class WindowsSpecificNonJvmOptsUtil {

    private final List<String> protectedOpts;

    public WindowsSpecificNonJvmOptsUtil() {
        List<String> opts = new ArrayList<String>();
        opts.add("-Djava.endorsed.dirs=");
        opts.add("-Dcatalina.base=");
        opts.add("-Dcatalina.home=");
        opts.add("-Djava.io.tmpdir=");
        opts.add("-Djava.util.logging.manager=");
        opts.add("-Djava.util.logging.config.file=");
        opts.add("-Dwrapper.dump.port=");
        protectedOpts = Collections.unmodifiableList(opts);
    }

    public List<String> mergeOpts(List<String> existingOpts, List<String> requestedOpts) {
        if (existingOpts == null) {
            existingOpts = new ArrayList<String>();
        }
        List<String> newOpts = new ArrayList<String>();
        newOpts.addAll(requestedOpts);
        for (String exsistingOpt : existingOpts) {
            String exsistingOptName = parseOptName(exsistingOpt);
            if (optPresent(exsistingOptName, protectedOpts) && !optPresent(exsistingOptName, newOpts)) {
                newOpts.add(exsistingOpt);
            }
        }
        return newOpts;
    }

    public List<String> removeProtectedOpts(List<String> requestedOpts) {
        List<String> newOpts = new ArrayList<String>();
        for (String requestedOpt : requestedOpts) {
            String requestedOptName = parseOptName(requestedOpt);
            if (!optPresent(requestedOptName, protectedOpts)) {
                newOpts.add(requestedOpt);
            }
        }
        return newOpts;
    }

    public String stripQuotes(String str) {
        if (str == null) {
            return null;
        }
        str = str.trim();
        if (str.length() > 1) {
            if (str.startsWith("\"") && str.endsWith("\"")) {
                return str.substring(1, str.length() - 1).trim();
            } else if (str.startsWith("'") && str.endsWith("'")) {
                return str.substring(1, str.length() - 1).trim();
            }
        }
        return str;
    }

    public String addQuotesIfNeeded(String str) {
        if (str == null) {
            return null;
        }
        str = str.trim();
        if (str.startsWith("-D") && str.contains(" ")) {
            StringBuilder strBuilder = new StringBuilder();
            String[] splitProperty = str.split("=", 2);
            strBuilder.append(splitProperty[0]);
            if (splitProperty.length == 2) {
                strBuilder.append("=");
                if (splitProperty[1].contains("\"")) {
                    strBuilder.append(splitProperty[1]);
                } else {
                    strBuilder.append("\"").append(splitProperty[1]).append("\"");
                }
            }
            return strBuilder.toString();
        } else {
            return str;
        }
    }

    /**
     * @param str the JVM options
     * @return the value of the string up to and including the equals sign. Or the whole string if there is no equals
     *         sign
     */
    private String parseOptName(String str) {
        int pos = str.indexOf("=");
        if (pos != -1) {
            return str.substring(0, pos + 1);
        }
        return str;
    }

    /**
     * @param opt the opt to check for
     * @param opts set of opts to check
     * @return true if the opts contains a value starting with the opt value
     */
    private boolean optPresent(String opt, List<String> opts) {
        for (String o : opts) {
            if (o.startsWith(opt)) {
                return true;
            }
        }
        return false;
    }

}
