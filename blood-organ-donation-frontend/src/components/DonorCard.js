import React from "react";
import { Card, CardContent, Typography, Grid, Avatar } from "@mui/material";
import { Bloodtype } from "@mui/icons-material";

export function DonorCard({ donor }) {
  return (
    <Card sx={{ mb: 2 }}>
      <CardContent>
        <Grid container spacing={2} alignItems="center">
          <Grid item>
            <Avatar sx={{ bgcolor: "#c62828" }}>
              <Bloodtype />
            </Avatar>
          </Grid>
          <Grid item xs>
            <Typography variant="h6">{donor.fullName || donor.donorName}</Typography>
            <Typography color="text.secondary">{donor.bloodGroup}</Typography>
            <Typography color="text.secondary">{donor.phone}</Typography>
          </Grid>
        </Grid>
      </CardContent>
    </Card>
  );
}
