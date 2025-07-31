@echo off
echo ========================================
echo Building and Running E-commerce Project
echo ========================================

cd /d "c:\Users\tsukk\Projects\ecom-java"

echo.
echo [1/4] Cleaning previous builds...
call mvn clean
if %errorlevel% neq 0 (
    echo ERROR: Failed to clean project
    pause
    exit /b 1
)

echo.
echo [2/4] Installing ecomdata module...
cd ecomdata
call mvn clean install
if %errorlevel% neq 0 (
    echo ERROR: Failed to build ecomdata module
    pause
    exit /b 1
)

echo.
echo [3/4] Building ecomms (JavaFX Desktop App)...
cd ..\ecomms
call mvn clean compile
if %errorlevel% neq 0 (
    echo ERROR: Failed to build ecomms module
    pause
    exit /b 1
)

echo.
echo [4/4] Building sbecomms (Spring Boot Web App)...
cd ..\sbecomms
call mvn clean compile
if %errorlevel% neq 0 (
    echo ERROR: Failed to build sbecomms module
    pause
    exit /b 1
)

echo.
echo ========================================
echo Build completed successfully!
echo ========================================
echo.
echo Choose which application to run:
echo 1. JavaFX Desktop Application (ecomms)
echo 2. Spring Boot Web Application (sbecomms)
echo 3. Exit
echo.
set /p choice="Enter your choice (1, 2, or 3): "

if "%choice%"=="1" (
    echo.
    echo Starting JavaFX Desktop Application...
    cd ..\ecomms
    call mvn javafx:run
) else if "%choice%"=="2" (
    echo.
    echo Starting Spring Boot Web Application...
    echo The application will be available at: http://localhost:8080
    cd ..\sbecomms
    call mvn spring-boot:run
) else if "%choice%"=="3" (
    echo Exiting...
    goto :end
) else (
    echo Invalid choice. Exiting...
    goto :end
)

:end
echo.
echo Press any key to exit...
pause > nul
