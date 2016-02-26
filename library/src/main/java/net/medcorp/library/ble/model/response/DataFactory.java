/*
 * COPYRIGHT (C) 2014 MED Enterprises LTD. All Rights Reserved.
 */
package net.medcorp.library.ble.model.response;

import android.bluetooth.BluetoothGattCharacteristic;

import net.medcorp.library.ble.datasource.GattAttributesDataSource;

public class DataFactory {
	
	public static ResponseData fromBluetoothGattCharacteristic(GattAttributesDataSource dataSource, final BluetoothGattCharacteristic characteristic, final String address) {
        // This is special handling for the Heart Rate Measurement profile.  Data parsing is
        // carried out as per profile specifications:
        // http://developer.bluetooth.org/gatt/characteristics/Pages/CharacteristicViewer.aspx?u=org.bluetooth.characteristic.heart_rate_measurement.xml
		if (dataSource.getNevoOtaCallbackCharacteristic().equals(characteristic.getUuid())){
			return new NevoRawDataImpl(characteristic, address);
		} else if (dataSource.getNevoOtaCallbackCharacteristic().equals(characteristic.getUuid())
				|| dataSource.getNevoOtaCharacteristic().equals(characteristic.getUuid())){
			return new FirmwareData(characteristic, address);
		}
        return new UnknownData();
	}

}
