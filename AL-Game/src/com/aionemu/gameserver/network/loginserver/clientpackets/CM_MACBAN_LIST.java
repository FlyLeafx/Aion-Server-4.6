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

package com.aionemu.gameserver.network.loginserver.clientpackets;

import com.aionemu.gameserver.network.BannedMacManager;
import com.aionemu.gameserver.network.loginserver.LsClientPacket;

/**
 *
 * @author KID
 *
 */
public class CM_MACBAN_LIST extends LsClientPacket {

	public CM_MACBAN_LIST(int opCode) {
		super(opCode);
	}

	@Override
	protected void readImpl() {
		BannedMacManager bmm = BannedMacManager.getInstance();
		int cnt = readD();
		for (int a = 0; a < cnt; a++) {
			bmm.dbLoad(readS(), readQ(), readS());
		}

		bmm.onEnd();
	}

	@Override
	protected void runImpl() {
		// ?
	}
}
