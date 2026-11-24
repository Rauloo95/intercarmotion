import { useState } from 'react'
import './App.css'
import { python_api, java_api } from './Services/Api.js'
import NavBar from './Components/NavBar'
import { BrowserRouter } from 'react-router-dom'
import { Routes, Route } from 'react-router-dom'
import Home from './Pages/Home.jsx'
import Login from './Pages/Login.jsx'
import Register from './Pages/Register.jsx'
import Favourites from './Pages/Favourites.jsx'
import { AuthProvider } from './Context/AuthContext.jsx'

function App() {
  console.log(python_api)
  console.log(java_api)

  return (

    <AuthProvider>
      <BrowserRouter>
        <div className="portal_1">
          <h1>Mi portal gataso</h1>
          <h2 className='java'>PYTHON API = {python_api}</h2>
          <h2 className='python'>JAVA API = {java_api}</h2>

          <NavBar>
            <Routes>

              <Route path="/" element={<Home />} />
              <Route path="/login" element={<Login />} />
              <Route path="/register" element={<Register />} />
              <Route path="/favourites" element={<Favourites />} />


            </Routes>

          </NavBar>

        </div>



      </BrowserRouter>

    </AuthProvider>





  )
}

export default App
