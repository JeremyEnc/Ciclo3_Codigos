library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity Multiplexor2bits is
    Port ( a,b,c,d,s : in  STD_LOGIC_VECTOR (1 downto 0);
           z : out  STD_LOGIC_VECTOR (1 downto 0));
end Multiplexor2bits;

architecture Behavioral of Multiplexor2bits is

begin

	With s Select
		
		z <= a when "00",
			  b when "01",
			  c when "10",
			  d when others;
				  
end Behavioral;

