----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    09:07:55 01/19/2022 
-- Design Name: 
-- Module Name:    ModeloRam1 - Behavioral 
-- Project Name: 
-- Target Devices: 
-- Tool versions: 
-- Description: 
--
-- Dependencies: 
--
-- Revision: 
-- Revision 0.01 - File Created
-- Additional Comments: 
--
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity ModeloRam1 is

	 generic(K: integer:= 8;
				A: integer:= 8);

    Port ( WR : in  STD_LOGIC;
           ADDR : in  STD_LOGIC_VECTOR (W-1 downto 0);
           DIN : in  STD_LOGIC_VECTOR (K-1 downto 0);
           DOUT : out  STD_LOGIC_VECTOR (K-1 downto 0));
			  
end ModeloRam1;

architecture Behavioral of ModeloRam1 is

begin


end Behavioral;

