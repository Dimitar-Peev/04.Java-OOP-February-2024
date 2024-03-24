package UnitTesting_Exercises.p06_TirePressureMonitoringSystem;

import org.junit.*;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class AlarmTest { 
    @Test
    public void when_presureLower_then_alarmSholudBeON(){
        Sensor sensor = Mockito.mock(Sensor.class);
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(15.5);
        Alarm alarm = new Alarm(sensor);
        alarm.check();
        assertTrue(alarm.getAlarmOn());
    }
    @Test
    public void when_presureGreater_then_alarmSholudBeON(){
        Sensor sensor = Mockito.mock(Sensor.class);
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(25.5);
        Alarm alarm = new Alarm(sensor);
        alarm.check();
        assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void when_presureNormal_then_alarmSholudBeOFF(){
        Sensor sensor = Mockito.mock(Sensor.class);
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(18.5);
        Alarm alarm = new Alarm(sensor);
        alarm.check();
        assertFalse(alarm.getAlarmOn());
    }
}
