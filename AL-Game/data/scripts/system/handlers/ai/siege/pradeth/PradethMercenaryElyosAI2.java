/**
 * This file is part of Aion-Lightning <aion-lightning.org>.
 *
 *  Aion-Lightning is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Aion-Lightning is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details. *
 *  You should have received a copy of the GNU General Public License
 *  along with Aion-Lightning.
 *  If not, see <http://www.gnu.org/licenses/>.
 */

package ai.siege.pradeth;

import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.model.DialogAction;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import com.aionemu.gameserver.utils.PacketSendUtility;

/**
 * @author Eloann
 */
@AIName("pradeth_mercenary_elyos")
public class PradethMercenaryElyosAI2 extends NpcAI2 {

	@Override
	protected void handleDialogStart(Player player) {
		if (player.getInventory().getItemCountByItemId(186000236) > 0) {
			super.handleDialogStart(player);
		} else {
			PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 27));
			PacketSendUtility.sendMessage(player, "You need item [item: 186000236] to ask reinforcements");
		}
	}

	@Override
	public boolean onDialogSelect(Player player, int dialogId, int questId, int extendedRewardIndex) {
		switch (DialogAction.getActionByDialogId(dialogId)) {
			case SETPRO1:
				spawn(273016, 2628.9766f, 2791.371f, 253.86087f, (byte) 61); // Stalwart
				spawn(273016, 2611.1191f, 2772.6743f, 253.83484f, (byte) 30); // Stalwart
				spawn(273016, 2689.5513f, 2598.0747f, 253.83484f, (byte) 119); // Stalwart
				spawn(273016, 2705.4612f, 2614.9175f, 253.83865f, (byte) 90); // Stalwart
				spawn(273046, 2575.6929f, 2610.3667f, 253.65619f, (byte) 75); // Archmagus
				spawn(273046, 2581.2778f, 2605.1582f, 253.65619f, (byte) 75); // Archmagus
				spawn(273046, 2739.7441f, 2775.4817f, 253.72882f, (byte) 15); // Archmagus
				spawn(273046, 2734.1306f, 2780.7148f, 253.72882f, (byte) 15); // Archmagus
				spawn(273031, 2717.3767f, 2738.2834f, 265.75882f, (byte) 33); // Sniper
				spawn(273031, 2698.919f, 2755.3105f, 265.75632f, (byte) 1); // Sniper
				spawn(273031, 2599.8613f, 2648.6938f, 265.75552f, (byte) 92); // Sniper
				spawn(273031, 2639.0042f, 2612.565f, 265.77414f, (byte) 59); // Sniper
				break;
			case SETPRO2:
				spawn(273031, 2667.2795f, 2592.56f, 268.32513f, (byte) 106); // Sniper
				spawn(273031, 2629.799f, 2770.936f, 265.8121f, (byte) 45); // Sniper
				spawn(273031, 2579.392f, 2672.4946f, 265.81476f, (byte) 75); // Sniper
				spawn(273031, 2621.6184f, 2632.5005f, 265.81476f, (byte) 76); // Sniper
				spawn(273031, 2736.454f, 2669.858f, 265.80414f, (byte) 106); // Sniper
				spawn(273031, 2775.1433f, 2654.893f, 262.82587f, (byte) 106); // Sniper
				spawn(273031, 2684.618f, 2557.8318f, 262.82587f, (byte) 106); // Sniper
				spawn(273031, 2627.3955f, 2578.351f, 262.82587f, (byte) 76); // Sniper
				spawn(273031, 2522.8257f, 2675.4504f, 262.82587f, (byte) 76); // Sniper
				spawn(273031, 2522.1343f, 2711.6804f, 262.82587f, (byte) 47); // Sniper
				spawn(273031, 2541.5078f, 2731.549f, 259.10098f, (byte) 41); // Sniper
				spawn(273031, 2632.497f, 2829.7847f, 262.82587f, (byte) 46); // Sniper
				spawn(273031, 2793.8599f, 2712.1128f, 262.82587f, (byte) 15); // Sniper
				break;
			case SETPRO3:
                spawn(273314, 2596.982f, 2737.708f, 266.19125f, (byte) 46); // Empty Aetheric Bombard
                spawn(273314, 2611.173f, 2752.241f, 266.19125f, (byte) 45); // Empty Aetheric Bombard
                spawn(273314, 2705.3672f, 2636.0967f, 266.20126f, (byte) 106); // Empty Aetheric Bombard
                spawn(273314, 2718.9968f, 2651.4675f, 266.20126f, (byte) 105); // Empty Aetheric Bombard
				break;
			default:
				break;
		}
		return true;
	}
}
