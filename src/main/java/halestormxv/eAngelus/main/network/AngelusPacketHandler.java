package halestormxv.eAngelus.main.network;

import halestormxv.eAngelus.main.Reference;
import halestormxv.eAngelus.main.network.packets.ChatUtil.PacketNoSpamChat;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;

public class AngelusPacketHandler 
{

	  public static final ThreadedNetworkWrapper INSTANCE = new ThreadedNetworkWrapper(Reference.NAME);

	  public static void init() 
	  {
	    INSTANCE.registerMessage(PacketNoSpamChat.Handler.class, PacketNoSpamChat.class, 0, Side.CLIENT);
	  }

	  public static void sendToAllAround(IMessage message, TileEntity te, int range) {
	    BlockPos p = te.getPos();
	    INSTANCE.sendToAllAround(message, new TargetPoint(te.getWorld().provider.getDimension(), p.getX(), p.getY(), p.getZ(), range));
	  }

	  public static void sendToAllAround(IMessage message, TileEntity te) {
	    sendToAllAround(message, te, 64);
	  }

	  public static void sendTo(IMessage message, EntityPlayerMP player) {
	    INSTANCE.sendTo(message, player);
	  }

	  public static void sendToServer(IMessage message) {
	    INSTANCE.sendToServer(message);
	  }
	}