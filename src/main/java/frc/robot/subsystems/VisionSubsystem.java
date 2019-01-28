/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.ReceiveData;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class VisionSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  ServerSocket ss;
  Socket socket;
  DataInputStream dataStream;

  public VisionSubsystem() {

  }

  public void openPort() {
    try {
      ss = new ServerSocket(5243);
      socket = ss.accept();
      dataStream = new DataInputStream(socket.getInputStream());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void receiveData() {
    while (true) {
      try {
        byte[] buffer = new byte[1024];
        int read = 0;
        while ((read = dataStream.read(buffer, 0, buffer.length)) != -1) {
          dataStream.read(buffer);
        }
        String output = new String(buffer);
        if (output != null) {
          System.out.println(output);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public void closePort() {
    try {
      dataStream.close();
      socket.close();
      ss.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ReceiveData());
  }
}
