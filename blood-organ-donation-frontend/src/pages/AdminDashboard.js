import React from "react";
import { Paper, Typography, Grid, Card, CardContent } from "@mui/material";

export default function AdminDashboard() {
  return (
    <Paper elevation={4} sx={{ maxWidth: 900, margin: "48px auto", p: 3 }}>
      <Typography variant="h5" gutterBottom>Admin Dashboard</Typography>
      <Grid container spacing={2}>
        <Grid item xs={12} sm={6} md={3}>
          <Card>
            <CardContent>
              <Typography color="text.secondary">Active Donors</Typography>
              <Typography variant="h5">0</Typography>
            </CardContent>
          </Card>
        </Grid>
        <Grid item xs={12} sm={6} md={3}>
          <Card>
            <CardContent>
              <Typography color="text.secondary">Pending Emergencies</Typography>
              <Typography variant="h5">0</Typography>
            </CardContent>
          </Card>
        </Grid>
        <Grid item xs={12} sm={6} md={3}>
          <Card>
            <CardContent>
              <Typography color="text.secondary">Total Recipients</Typography>
              <Typography variant="h5">0</Typography>
            </CardContent>
          </Card>
        </Grid>
        <Grid item xs={12} sm={6} md={3}>
          <Card>
            <CardContent>
              <Typography color="text.secondary">Organ Requests</Typography>
              <Typography variant="h5">0</Typography>
            </CardContent>
          </Card>
        </Grid>
      </Grid>
      <Typography sx={{ mt: 3 }}>Coming soon: Advanced analytics, charts, and management tools!</Typography>
    </Paper>
  );
}
