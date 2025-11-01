import React, { useState } from "react";
import { Box, Button, TextField, MenuItem, Snackbar, Alert, Typography, Paper } from "@mui/material";
import axios from "../api";

export default function RegisterOrganDonor() {
  const [form, setForm] = useState({
    donorName: "", organType: "", email: "", phone: "", bloodGroup: "", age: "", city: ""
  });
  const [open, setOpen] = useState(false);
  const [feedback, setFeedback] = useState({ type: "success", message: "" });
  const organTypes = ["Kidney", "Liver", "Heart", "Lung", "Pancreas", "Intestine", "Cornea"];
  const bloodGroups = ["A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"];

  function handleChange(e) { setForm({ ...form, [e.target.name]: e.target.value }); }
  async function handleSubmit(e) {
    e.preventDefault();
    try {
      await axios.post("/api/organ-donors", form);
      setFeedback({ type: "success", message: "Organ donation registered!" });
      setForm({ donorName:"",organType:"",email:"",phone:"",bloodGroup:"",age:"",city:"" });
    } catch (err) {
      setFeedback({ type: "error", message: "Error. Please check input." });
    }
    setOpen(true);
  }

  return (
    <Paper elevation={4} sx={{ maxWidth: 500, margin: "42px auto", padding: 3 }}>
      <Typography variant="h5" gutterBottom>Organ Donor Registration</Typography>
      <Box component="form" onSubmit={handleSubmit} noValidate>
        <TextField name="donorName" label="Donor Name" fullWidth margin="normal" required onChange={handleChange} value={form.donorName} />
        <TextField name="organType" label="Organ Type" select fullWidth margin="normal" required onChange={handleChange} value={form.organType}>
          {organTypes.map(type => (<MenuItem key={type} value={type}>{type}</MenuItem>))}
        </TextField>
        <TextField name="email" label="Email" type="email" fullWidth margin="normal" required onChange={handleChange} value={form.email} />
        <TextField name="phone" label="Phone" fullWidth margin="normal" required onChange={handleChange} value={form.phone} />
        <TextField name="bloodGroup" label="Blood Group" select fullWidth margin="normal" required onChange={handleChange} value={form.bloodGroup}>
          {bloodGroups.map(bg => (<MenuItem key={bg} value={bg}>{bg}</MenuItem>))}
        </TextField>
        <TextField name="age" label="Age" type="number" fullWidth margin="normal" required inputProps={{ min: 18 }} onChange={handleChange} value={form.age} />
        <TextField name="city" label="City" fullWidth margin="normal" onChange={handleChange} value={form.city} />
        <Button type="submit" variant="contained" color="primary" fullWidth sx={{ mt: 2 }}>Submit</Button>
      </Box>
      <Snackbar open={open} autoHideDuration={4000} onClose={() => setOpen(false)} anchorOrigin={{ vertical: "bottom", horizontal: "center" }}>
        <Alert onClose={() => setOpen(false)} severity={feedback.type}>{feedback.message}</Alert>
      </Snackbar>
    </Paper>
  );
}
