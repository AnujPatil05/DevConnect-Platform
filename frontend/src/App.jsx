// Inside App.jsx

import React from 'react';
import LoginForm from './components/LoginForm.jsx'; // <-- New import

function App() {
  const handleLogin = (token) => {
    // We will save the token here in the next step
    console.log("JWT received:", token);
  };

  return (
    <div>
      <h1>DevConnect Platform</h1>
      <LoginForm onLoginSuccess={handleLogin} /> {/* <-- New component */}
    </div>
  );
}

export default App;