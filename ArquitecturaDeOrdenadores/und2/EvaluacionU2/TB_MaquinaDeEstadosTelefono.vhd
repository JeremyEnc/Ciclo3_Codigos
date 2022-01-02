--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   21:09:04 01/01/2022
-- Design Name:   
-- Module Name:   C:/Users/jere_/Documents/circuitosISE/EvaluacionSumativaU2/TB_MaquinaDeEstadosTelefono.vhd
-- Project Name:  EvaluacionSumativaU2
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: MaquinaDeEstadosTelefono
-- 
-- Dependencies:
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
--
-- Notes: 
-- This testbench has been automatically generated using types std_logic and
-- std_logic_vector for the ports of the unit under test.  Xilinx recommends
-- that these types always be used for the top-level I/O of a design in order
-- to guarantee that the testbench will bind correctly to the post-implementation 
-- simulation model.
--------------------------------------------------------------------------------
LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
 
-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--USE ieee.numeric_std.ALL;
 
ENTITY TB_MaquinaDeEstadosTelefono IS
END TB_MaquinaDeEstadosTelefono;
 
ARCHITECTURE behavior OF TB_MaquinaDeEstadosTelefono IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT MaquinaDeEstadosTelefono
    PORT(
         clk : IN  std_logic;
         llamar : IN  std_logic;
         nv : IN  std_logic;
         cr : IN  std_logic;
         dm : IN  std_logic;
         lf : IN  std_logic;
         reposo : OUT  std_logic;
         marcando : OUT  std_logic;
         llamando : OUT  std_logic;
         espera : OUT  std_logic;
         finalizado : OUT  std_logic;
         Q : OUT  std_logic_vector(3 downto 0)
        );
    END COMPONENT;
    

   --Inputs
   signal clk : std_logic := '0';
   signal llamar : std_logic := '0';
   signal nv : std_logic := '0';
   signal cr : std_logic := '0';
   signal dm : std_logic := '0';
   signal lf : std_logic := '0';

 	--Outputs
   signal reposo : std_logic;
   signal marcando : std_logic;
   signal llamando : std_logic;
   signal espera : std_logic;
   signal finalizado : std_logic;
   signal Q : std_logic_vector(3 downto 0);
	
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: MaquinaDeEstadosTelefono PORT MAP (
          clk => clk,
          llamar => llamar,
          nv => nv,
          cr => cr,
          dm => dm,
          lf => lf,
          reposo => reposo,
          marcando => marcando,
          llamando => llamando,
          espera => espera,
          finalizado => finalizado,
          Q => Q
        );
 
   -- Stimulus process
   stim_proc: process
   begin		
      
		llamar <= '0';
		nv <= '0';
		cr <= '0';
		lf <= '0';
		dm <= '0';
		wait for 10 us;
		llamar <= '1';
		nv <= '0';
		cr <= '0';
		lf <= '0';
		dm <= '0';
		wait for 10 us;
		llamar <= '0';
		nv <= '1';
		cr <= '0';
		lf <= '0';
		dm <= '1';
		wait for 10 us;
		llamar <= '0';
		nv <= '0';
		cr <= '0';
		lf <= '1';
		dm <= '0';
		wait for 10 us;
		llamar <= '1';
		nv <= '0';
		cr <= '0';
		lf <= '0';
		dm <= '0';
		wait for 10 us;
		llamar <= '0';
		nv <= '1';
		cr <= '1';
		lf <= '0';
		dm <= '0';
		wait for 10 us;
		llamar <= '0';
		nv <= '0';
		cr <= '0';
		lf <= '0';
		dm <= '1';
		wait for 10 us;
		llamar <= '0';
		nv <= '0';
		cr <= '0';
		lf <= '1';
		dm <= '0';
		wait for 10 us;
		llamar <= '0';
		nv <= '0';
		cr <= '0';
		lf <= '0';
		dm <= '0';
		wait for 10 us;
	
      wait;
   end process;

END;
