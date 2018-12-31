/*
 * Copyright (C) 2018 Chikachi and other contributors
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses.
 */

package net.discordintegration.core.config.linking;

import com.google.gson.annotations.Since;
import net.discordintegration.core.config.Configuration;

import java.util.Random;
import java.util.UUID;

public class DiscordToMinecraftLinkingRequest extends AbstractLinkingRequest {
    private static Random rand = new Random();

    @Since(3.0)
    private long discordId;

    private DiscordToMinecraftLinkingRequest() {
    }

    static DiscordToMinecraftLinkingRequest create(long discordId) {
        DiscordToMinecraftLinkingRequest request = new DiscordToMinecraftLinkingRequest();
        request.discordId = discordId;
        request.generateCode();
        Configuration.getLinking().addRequest(request);
        return request;
    }

    public long getDiscordId() {
        return discordId;
    }

    public void executeLinking(UUID minecraftUUID) {
        Configuration.getLinking().executeRequest(this, minecraftUUID);
    }
}
