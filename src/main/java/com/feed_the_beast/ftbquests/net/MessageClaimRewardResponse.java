package com.feed_the_beast.ftbquests.net;

import com.feed_the_beast.ftbquests.FTBQuests;
import com.feed_the_beast.ftbquests.util.NetUtils;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;

/**
 * @author LatvianModder
 */
public class MessageClaimRewardResponse extends MessageBase
{
	private final UUID player;
	private final int id;

	MessageClaimRewardResponse(PacketBuffer buffer)
	{
		player = NetUtils.readUUID(buffer);
		id = buffer.readVarInt();
	}

	public MessageClaimRewardResponse(UUID p, int i, int t)
	{
		player = p;
		id = i;
	}

	@Override
	public void write(PacketBuffer buffer)
	{
		NetUtils.writeUUID(buffer, player);
		buffer.writeVarInt(id);
	}

	@Override
	public void handle(NetworkEvent.Context context)
	{
		FTBQuests.NET_PROXY.claimReward(player, id);
	}
}