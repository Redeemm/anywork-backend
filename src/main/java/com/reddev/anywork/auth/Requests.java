package com.reddev.anywork.auth;



import com.reddev.anywork.model.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Requests {

  public Role role;
  public String profilePhotoUrl;
  public String idCardPhotoUrl;
  public String companyPhoneNumber;
  public String destination;
  public String departurePoint;
  public String password;
  public String ticketPrice;
  public String tripStatus;
  public String locationName;
  public String locationCoordinates;
  public String locationImageUrl;
  public String primaryBusAssigned;
  public String driverLicenseNumber;
  public String firstName;
  public String lastName;
  public String phoneNumber;
  public String email;
  public String profileUrl;
  public String location;
  public String serviceType;
  public String busVehicleNumber;
  public Long numberOfSeats;
  public Long bookedSeats;
  public Long availableSeat;
  public UUID uuid;
  public Long id;
}
