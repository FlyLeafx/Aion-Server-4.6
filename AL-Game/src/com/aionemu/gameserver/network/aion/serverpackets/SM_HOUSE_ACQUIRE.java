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

package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * @author Rolandas
 */
public class SM_HOUSE_ACQUIRE extends AionServerPacket {

	private int playerId;
	private int address;
	private boolean acquire;

	public SM_HOUSE_ACQUIRE(int playerId, int address, boolean acquire) {
		this.playerId = playerId;
		this.address = address;
		this.acquire = acquire;
	}

	@Override
	protected void writeImpl(AionConnection con) {
		writeD(playerId);
		writeD(address);
		writeD(acquire ? 1 : 0); // now it has value 2 sometimes, maybe initial door state ?
	}
}
