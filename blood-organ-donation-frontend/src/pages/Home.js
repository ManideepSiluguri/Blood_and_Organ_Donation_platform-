import React from "react";
import { Link } from "react-router-dom";
import { Box, Typography, Button, Paper, Grid } from "@mui/material";
import { Bloodtype } from "@mui/icons-material";

export default function Home() {
  return (
    <NotificationCenter
          donorId={userId}
          bloodGroup={userBloodGroup}
          city={userCity}
    />

    <Paper elevation={3} sx={{ maxWidth: 700, margin: "48px auto", padding: 4 }}>
      <Box sx={{ textAlign: "center", mb: 4 }}>
        <Bloodtype color="primary" sx={{ fontSize: 64, mb: 2 }} />
        <Typography variant="h3" color="primary" gutterBottom>
          Save Lives Together
        </Typography>
        <Typography variant="h6" color="secondary" sx={{ mb: 4 }}>
          Donate blood or organs, request help instantly, join our life-saving community.
        </Typography>
      </Box>

      <Grid container spacing={2}>
        <Grid item xs={12} sm={6}>
          <Paper sx={{ p: 3, textAlign: "center" }}>
            <Typography variant="h6" gutterBottom>Blood Donation</Typography>
            <Button variant="contained" component={Link} to="/register-blood-donor" fullWidth sx={{ mb: 1 }}>
              Register as Blood Donor
            </Button>
            <Button variant="outlined" component={Link} to="/search-blood-donors" fullWidth>
              Search Blood Donors
            </Button>
          </Paper>
        </Grid>

        <Grid item xs={12} sm={6}>
          <Paper sx={{ p: 3, textAlign: "center" }}>
            <Typography variant="h6" gutterBottom>Organ Donation</Typography>
            <Button variant="contained" component={Link} to="/register-organ-donor" fullWidth sx={{ mb: 1 }}>
              Register as Organ Donor
            </Button>
            <Button variant="outlined" component={Link} to="/search-organ-donors" fullWidth>
              Search Organ Donors
            </Button>
          </Paper>
        </Grid>

        <Grid item xs={12}>
          <Paper sx={{ p: 3, textAlign: "center" }}>
            <Typography variant="h6" gutterBottom>Emergency Request</Typography>
            <Button variant="contained" color="error" component={Link} to="/emergency" fullWidth>
              Request Emergency Blood/Organ
            </Button>
          </Paper>
        </Grid>
      </Grid>
    </Paper>
  );
}
