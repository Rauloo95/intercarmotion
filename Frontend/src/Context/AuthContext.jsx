import { createContext, useContext, useState } from "react"

const AuthContext = createContext()

export function AuthProvider({ children }) {
    const [token, setToken] = useState(() => localStorage.getItem("token"))
    const [user, setUser] = useState(() => {
        const savedUser = localStorage.getItem("user")
        return savedUser ? JSON.parse(savedUser) : null
    });

    const login = (newToken, userData) => {
        localStorage.setItem("token", newToken)
        if (userData) {
            localStorage.setItem("user", JSON.stringify(userData));
        }
        setToken(newToken);
        setUser(userData || null);


    };

    const logOut = () => {
        localStorage.removeItem("token");
        localStorage.removeItem("user");
        setToken(null);
        setUser(null);
    };


    return (
        <AuthContext.Provider
            value={{
                token,
                user,
                isAuthenticated: !!token,
                login,
                logOut
            }}
        >
            {children}

        </AuthContext.Provider>
    )
}
export function useAuth() {
    return useContext(AuthContext)
}