package com.neu.bloodbankmanagement.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Donation_History")
public class DonationHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id", unique = true, nullable = false)
	private long id;
	
	@Column(name="donation_date")
	private Date date;
	
	@ManyToOne
	@JoinColumn(name="Blood_Bank_Id")
	private BloodBank bloodBank;
	
	@ManyToOne
	@JoinColumn(name="Donor_Id")
	private Donor donor;

	public DonationHistory() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BloodBank getBloodBank() {
		return bloodBank;
	}

	public void setBloodBank(BloodBank bloodBank) {
		this.bloodBank = bloodBank;
	}

	public Donor getDonor() {
		return donor;
	}

	public void setDonor(Donor donor) {
		this.donor = donor;
	}

}
