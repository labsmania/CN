set ns [new Simulator]

set nf [open 2.nam w]
$ns namtrace-all $nf

set tf [open 2.tr w]
$ns trace-all $tf

proc finish {} {
    global ns nf tf
    $ns flush-trace
    close $nf
    close $tf
    exec nam 2.nam &
    exit 0
}

# Create nodes
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]
set n5 [$ns node]

# Define duplex links between nodes
$ns duplex-link $n0 $n1 10Mb 1ms DropTail
$ns duplex-link $n1 $n2 10Mb 1ms DropTail
$ns duplex-link $n2 $n3 10Mb 1ms DropTail
$ns duplex-link $n3 $n4 10Mb 1ms DropTail
$ns duplex-link $n4 $n5 10Mb 1ms DropTail
$ns duplex-link $n5 $n0 10Mb 1ms DropTail

# Set up TCP agent and attach to node n0
set tcp0 [new Agent/TCP]
$tcp0 set class_ 1
$ns attach-agent $n0 $tcp0

# Set up CBR application
set cbr0 [new Application/Traffic/CBR]
$cbr0 set packetSize_ 500
$cbr0 set interval_ 0.005
$cbr0 attach-agent $tcp0

# Set up TCP Sink and attach to node n5
set sink0 [new Agent/TCPSink]
$ns attach-agent $n5 $sink0

# Connect TCP agent to sink
$ns connect $tcp0 $sink0

# Schedule CBR traffic
$ns at 0.5 "$cbr0 start"
$ns at 4.0 "$cbr0 stop"

# Schedule simulation finish
$ns at 5.0 "finish"
$ns run
