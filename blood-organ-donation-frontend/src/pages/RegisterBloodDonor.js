import React, { useState } from "react";
import { Box, Button, TextField, MenuItem, Snackbar, Alert, Typography, Paper } from "@mui/material";
import axios from "../api";

export default function RegisterBloodDonor() {
  const [form, setForm] = useState({
    firstName: "", lastName: "", email: "", phone: "",
    bloodGroup: "", age: "", gender: "", address: ""
  });
  const [open, setOpen] = useState(false);
  const [feedback, setFeedback] = useState({ type: "success", message: "" });
  const bloodGroups = ["A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"];

  function handleChange(e) { setForm({ ...form, [e.target.name]: e.target.value }); }
  async function handleSubmit(e) {
    e.preventDefault();
    try {
      await axios.post("/api/blood-donors", form);
      setFeedback({ type: "success", message: "Blood donation registered!" });
      setForm({ firstName:"",lastName:"",email:"",phone:"",bloodGroup:"",age:"",gender:"",address:"" });
    } catch (err) {
      setFeedback({ type: "error", message: "Error. Please check input." });
    }
    setOpen(true);
  }

  return (
    <Paper elevation={4} sx={{ maxWidth: 480, margin: "42px auto", padding: 3 }}>
      <Typography variant="h5" gutterBottom>Blood Donor Registration</Typography>
      <Box component="form" onSubmit={handleSubmit} noValidate>
        <TextField name="firstName" label="First Name" fullWidth margin="normal" required onChange={handleChange} value={form.firstName} />
        <TextField name="lastName" label="Last Name" fullWidth margin="normal" required onChange={handleChange} value={form.lastName} />
        <TextField name="email" label="Email" type="email" fullWidth margin="normal" required onChange={handleChange} value={form.email} />
        <TextField name="phone" label="Phone" fullWidth margin="normal" required onChange={handleChange} value={form.phone} />
        <TextField name="bloodGroup" label="Blood Group" select fullWidth margin="normal" required onChange={handleChange} value={form.bloodGroup}>
          {bloodGroups.map(bg => (<MenuItem key={bg} value={bg}>{bg}</MenuItem>))}
        </TextField>
        <TextField name="age" label="Age" type="number" fullWidth margin="normal" required inputProps={{ min: 18 }} onChange={handleChange} value={form.age} />
        <TextField name="gender" label="Gender" select fullWidth margin="normal" onChange={handleChange} value={form.gender}>
          <MenuItem value="Male">Male</MenuItem>
          <MenuItem value="Female">Female</MenuItem>
          <MenuItem value="Other">Other</MenuItem>
        </TextField>
        <TextField name="address" label="Address" fullWidth margin="normal" onChange={handleChange} value={form.address} />
        <Button type="submit" variant="contained" color="primary" fullWidth sx={{ mt: 2 }}>Submit</Button>
      </Box>
      <Snackbar open={open} autoHideDuration={4000} onClose={() => setOpen(false)} anchorOrigin={{ vertical: "bottom", horizontal: "center" }}>
        <Alert onClose={() => setOpen(false)} severity={feedback.type}>{feedback.message}</Alert>
      </Snackbar>
    </Paper>
  );
}
